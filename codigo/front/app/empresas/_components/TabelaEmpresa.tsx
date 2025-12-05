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

export default function TabelaEmpresas() {

  const queryClient = useQueryClient();

  const { data, refetch } = useQuery<EmpresaParceira[]>({
    queryKey: ['empresas'],
    queryFn: async () => {
      const response = await api.get('http://48.217.82.183:8080/api/empresas-parceiras')
      return response.data
    }
  })

  // Definição das colunas
  const columns: ColumnDef<any>[] = [
    {
      accessorKey: 'id',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          ID
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.id}</div>,
    },
    {
      accessorKey: 'cnpj',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          CNPJ
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.cnpj}</div>,
    },
    {
      accessorKey: 'razaoSocial',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Razão Social
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.razaoSocial}</div>,
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
                await EmpresaRequisicao.Excluir(row.original.id)
                queryClient.invalidateQueries({queryKey: ['empresas']});
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