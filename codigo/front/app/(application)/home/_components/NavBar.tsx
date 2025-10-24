'use client'

import { Building2, LogOut, User } from 'lucide-react'
import { Button } from '@/components/ui/button'
import { useUsuario } from '@/contexts/user.context'
import Sessao from '@/lib/utils/Sessao'
import { useRouter } from 'next/navigation'
import { AlunoResponse } from '@/types/Usuario/usuario.response'
import { EmpresaParceira } from '@/types/Empresa/empresa.response'
import { TipoUsuario } from '@/types/Usuario/enum'
import { useEffect } from 'react'
import { Utils } from '@/lib/utils/utils'

export default function Navbar() {
  const { usuario } = useUsuario()
  const router = useRouter()

    const decoded = Utils.Sessao.decodificarToken()

  return (
    <nav className="w-full bg-primary text-foreground px-6 py-3 shadow-md">
      <div className="flex justify-end items-center gap-4">

        {decoded?.tipo === TipoUsuario.ALUNO && (
          <AlunoButton aluno={usuario as AlunoResponse} />
        )}

        {decoded?.tipo === TipoUsuario.EMPRESA_PARCEIRA && (
          <EmpresaButton empresa={usuario as EmpresaParceira} />
        )}

        <Button
          variant="outline"
          className="text-foreground border-secondary hover:bg-secondary hover:cursor-pointer hover:text-white h-10 px-3"
          onClick={() => {
            Sessao.limparTodos()
            router.push("/login")
          }}
        >
          <LogOut className="h-4 w-4 mr-1" />
          Sair
        </Button>
      </div>
    </nav>
  )
}


type AlunoButtonProps = {
  aluno: AlunoResponse
}

function AlunoButton({ aluno }: AlunoButtonProps) {
    const router = useRouter()
  return (
    <Button
        className="flex items-center gap-3 h-10 p-2"
        variant="ghost"
        onClick={() => router.push("/perfil")}
    >
      <div className="flex flex-col justify-center items-end leading-tight">
        <span className="font-medium text-white">{aluno.nome}</span>
        <span className="text-sm text-green-500 font-bold">
          Saldo: R$ {aluno.saldo.toFixed(2)}
        </span>
      </div>

      <div className="bg-secondary/80 p-2 rounded-full flex-shrink-0">
        <User className="h-5 w-5 text-white" />
      </div>
    </Button>
  )
}


type EmpresaButtonProps = {
  empresa: EmpresaParceira
}

function EmpresaButton({ empresa }: EmpresaButtonProps) {
    const router = useRouter()
  return (
    <Button
        className="flex items-center gap-3 h-10 p-2"
        variant="ghost"
        onClick={() => router.push("/perfil")}
    >
      <div className="flex flex-col justify-center items-end leading-tight">
        <span className="font-medium text-white">{empresa?.razaoSocial}</span>
      </div>

      <div className="bg-secondary/80 p-2 rounded-full flex-shrink-0">
        <Building2 className="h-5 w-5 text-white" />
      </div>
    </Button>
  )
}