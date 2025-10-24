'use client'

import { LogOut, User } from 'lucide-react'
import { Button } from '@/components/ui/button'
import { useUsuario } from '@/contexts/user.context'
import Sessao from '@/lib/utils/Sessao'
import { useRouter } from 'next/navigation'

export default function Navbar() {
  const { usuario } = useUsuario()
  const router = useRouter()

  return (
    <nav className="w-full bg-primary text-foreground px-6 py-3 shadow-md">
      <div className="flex justify-end items-center">

        <div className="flex items-center gap-4">
            <Button
                className="flex items-center gap-3 h-10 p-2"
                variant="ghost"
                onClick={() => console.log("Botão do usuário clicado")}
            >
                <div className="flex flex-col justify-center items-end leading-tight">
                <span className="font-medium text-white">{usuario?.nome}</span>
                {usuario?.saldo !== undefined && (
                    <span className="text-sm text-green-500 font-bold">
                    Saldo: R$ {usuario?.saldo.toFixed(2)}
                    </span>
                )}
                </div>

                <div className="bg-secondary/80 p-2 rounded-full flex-shrink-0">
                    <User className="h-5 w-5 text-white" />
                </div>
            </Button>

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
      </div>
    </nav>
  )
}