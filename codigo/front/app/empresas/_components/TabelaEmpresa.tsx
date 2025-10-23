'use client'

import { useQuery } from '@tanstack/react-query'
import { ColumnDef } from '@tanstack/react-table'
import { Button } from '@/components/ui/button'
import { DataTable } from '@/components/ui/data-tables'
import { ArrowUpDown } from 'lucide-react'
import { DropdownMenu, DropdownMenuTrigger, DropdownMenuContent, DropdownMenuItem } from '@/components/ui/dropdown-menu'
import api from '@/lib/axios'
import { BsThreeDots } from 'react-icons/bs'

export default function TabelaEmpresas() {
  // Query para buscar todas as empresas
  const { data, refetch } = useQuery<any[]>({
    queryKey: ['empresas'],
    queryFn: async () => {
      const response = await api.get('http://localhost:8080/api/empresas-parceiras')
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
    }
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