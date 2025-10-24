'use client'

import { useQuery, useQueryClient } from '@tanstack/react-query'
import { ColumnDef } from '@tanstack/react-table'
import { Button } from '@/components/ui/button'
import { DataTable } from '@/components/ui/data-tables'
import { ArrowUpDown } from 'lucide-react'
import { DropdownMenu, DropdownMenuTrigger, DropdownMenuContent, DropdownMenuItem } from '@/components/ui/dropdown-menu'
import api from '@/lib/axios'
import { BsThreeDots } from 'react-icons/bs'
import { EmpresaRequisicao } from '@/server/Empresa'
import { EmpresaParceira } from '@/types/Empresa/empresa.response'
import { AlunoResponse } from '@/types/Usuario/usuario.response'
import { AlunoRequisicao } from '@/server/Aluno'

export default function TabelaAlunos() {
  const queryClient = useQueryClient()

  const { data, refetch } = useQuery<AlunoResponse[]>({
    queryKey: ['alunos'],
    queryFn: async () => {
      const response = await AlunoRequisicao.BuscarTodos()
      return response.data ?? []
    }
  })

  // Definição das colunas
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
          Instituição
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
                variant="destructive"
              onClick={async (e) => {
                e.stopPropagation()
                // await EmpresaRequisicao.Excluir(row.original.id)
                // queryClient.invalidateQueries({queryKey: ['empresas']});
              }}
            >
              Excluir
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      ),
    },
  ]

  return (
    <div className="w-3xl">
      <DataTable
        columnDef={columns}
        data={data || []}
        onClickRow={() => {}}
      />
    </div>
  )
}