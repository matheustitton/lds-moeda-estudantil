'use client'

import { Utils } from '@/lib/utils/utils'
import { AlunoRequisicao } from '@/server/Aluno'
import { EmpresaRequisicao } from '@/server/Empresa'
import { EmpresaParceira } from '@/types/Empresa/empresa.response'
import { ITipoUsuario, TipoUsuario } from '@/types/Usuario/enum'
import { AlunoResponse } from '@/types/Usuario/usuario.response'
import { createContext, ReactNode, useContext, useEffect, useState } from 'react'

type UsuarioContextType = {
  usuario: ITipoUsuario | null
  setUser: (usuario: ITipoUsuario | null) => void
}

type UsuarioContextProviderProps = {
  children: ReactNode
}

const UsuarioContextDefaultValues: UsuarioContextType = {
  usuario: null,
  setUser: () => {},
}

export const UsuarioContext = createContext<UsuarioContextType>(UsuarioContextDefaultValues)

export function useUsuario() {
  return useContext(UsuarioContext)
}

export function UsuarioContextProvider({ children }: UsuarioContextProviderProps) {
  const [usuario, setUser] = useState<ITipoUsuario | null | undefined>(undefined)

  useEffect(() => {
    const token = Utils.Sessao.buscarTokenAcesso() ?? null;
    if(!token) return;

    const decoded = Utils.Sessao.decodificarToken()
    
    if(decoded?.tipo == TipoUsuario.ALUNO){
        AlunoRequisicao.GetById(Number(decoded.sub)).then((response) => {
            console.log(response.data)
            setUser(response.data)
        });
    } else if(decoded?.tipo == TipoUsuario.EMPRESA_PARCEIRA){
        EmpresaRequisicao.GetById(Number(decoded.sub)).then((response) => {
            console.log(response.data)
            setUser(response.data)
        });
    }
  }, [])

  if (usuario === undefined) {
    return null
  }

  return <UsuarioContext.Provider value={{ usuario, setUser }}>{children}</UsuarioContext.Provider>
}