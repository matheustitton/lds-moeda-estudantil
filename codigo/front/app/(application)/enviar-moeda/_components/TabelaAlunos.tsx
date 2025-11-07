'use client'
import { useState } from "react"
import { Button } from "@/components/ui/button"
import { DataTable } from "@/components/ui/data-tables"
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu"
import { useUsuario } from "@/contexts/user.context"
import { InstituicaoEnsinoRequisicao } from "@/server/InstituicaoEnsino"
import { ProfessorResponse } from "@/types/Professor/professor.response"
import { AlunoResponse } from "@/types/Usuario/usuario.response"
import { useQuery } from "@tanstack/react-query"
import { ColumnDef } from "@tanstack/react-table"
import { ArrowUpDown } from "lucide-react"
import { BsThreeDots } from "react-icons/bs"
import EnviarMoeda from "./ModalEnviarMoeda"
import ModalEnviarMoeda from "./ModalEnviarMoeda"

export default function TabelaAlunos() {
  const { usuario } = useUsuario();
  const [open, setOpen] = useState(false)
  const [alunoSelecionado, setAlunoSelecionado] = useState<AlunoResponse | null>(null)

  const { data } = useQuery<AlunoResponse[]>({
    queryKey: ['alunos'],
    queryFn: async () => {
      if (!usuario) return []
      const response = await InstituicaoEnsinoRequisicao.BuscarAlunos((usuario as ProfessorResponse).instituicao.id)
      return response.data ?? []
    }
  })

  const handleEnviarMoedas = (aluno: AlunoResponse) => {
    setAlunoSelecionado(aluno)
    setOpen(true)
  }

  const columns: ColumnDef<AlunoResponse>[] = [
    {
      accessorKey: 'aluno',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Aluno
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.nome}</div>,
    },
    {
      accessorKey: 'curso',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Curso
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.curso}</div>,
    },
    {
      accessorKey: 'instituicao',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Instituição
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.instituicao.nome}</div>,
    },
    {
      accessorKey: 'saldo',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Saldo
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.saldo}</div>,
    },
    {
      id: "actions",
      enableHiding: false,
      cell: ({ row }) => (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" className="h-8 w-8 !p-2">
              <BsThreeDots />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuItem
              className="hover:cursor-pointer"
              onClick={(e) => {
                e.stopPropagation()
                handleEnviarMoedas(row.original)
              }}
            >
              Enviar moedas
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      ),
    },
  ]

  return (
    <div className="flex h-auto w-auto mx-8 align-middle">
      <DataTable
        columnDef={columns}
        data={data || []}
        onClickRow={() => {}}
      />

      {alunoSelecionado && (
        <ModalEnviarMoeda
          open={open}
          aluno={alunoSelecionado}
          idProfessor={(usuario as ProfessorResponse).id}
          onClose={() => setOpen(false)}
        />
      )}
    </div>
  )
}