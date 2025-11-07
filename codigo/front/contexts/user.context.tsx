'use client'

import { Utils } from '@/lib/utils/utils'
import { AlunoRequisicao } from '@/server/Aluno'
import { EmpresaRequisicao } from '@/server/Empresa'
import { ProfessorRequisicao } from '@/server/Professor'
import { ITipoUsuario, TipoUsuario } from '@/types/Usuario/enum'
import { createContext, ReactNode, useContext, useState } from 'react'
import { useQuery } from '@tanstack/react-query'

type UsuarioContextType = {
  usuario: ITipoUsuario | null
  setUser: (usuario: ITipoUsuario | null) => void
  refetchUsuario: () => void
  isLoading: boolean
}

type UsuarioContextProviderProps = {
  children: ReactNode
}

const UsuarioContextDefaultValues: UsuarioContextType = {
  usuario: null,
  setUser: () => {},
  refetchUsuario: () => {},
  isLoading: false,
}

export const UsuarioContext = createContext<UsuarioContextType>(UsuarioContextDefaultValues)

export function useUsuario() {
  return useContext(UsuarioContext)
}

export function UsuarioContextProvider({ children }: UsuarioContextProviderProps) {
  const [usuario, setUser] = useState<ITipoUsuario | null>(null)

  const token = Utils.Sessao.buscarTokenAcesso()
  const decoded = Utils.Sessao.decodificarToken()

  const { data, refetch, isFetching } = useQuery({
    queryKey: ['usuario'],
    queryFn: async () => {
      if (!token || !decoded) return null

      const id = Number(decoded.sub)
      const tipo = decoded.tipo as TipoUsuario

      switch (tipo) {
        case TipoUsuario.ALUNO: {
          const res = await AlunoRequisicao.GetById(id)
          return res.data ?? null
        }
        case TipoUsuario.EMPRESA_PARCEIRA: {
          const res = await EmpresaRequisicao.GetById(id)
          return res.data ?? null
        }
        case TipoUsuario.PROFESSOR: {
          const res = await ProfessorRequisicao.GetById(id)
          return res.data ?? null
        }
        default:
          return null
      }
    },
    enabled: !!token, // só executa se houver token
    staleTime: 1000 * 60 * 5, // cache por 5 minutos
  })

  // Atualiza estado local quando o useQuery retorna
  if (data && data !== usuario) {
    setUser(data)
  }

  if(!data){
    return;
  }

  return (
    <UsuarioContext.Provider
      value={{
        usuario: data ?? usuario,
        setUser,
        refetchUsuario: refetch,
        isLoading: isFetching,
      }}
    >
      {children}
    </UsuarioContext.Provider>
  )
}