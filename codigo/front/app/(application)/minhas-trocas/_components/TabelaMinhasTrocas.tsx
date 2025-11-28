'use client'
import { Button } from "@/components/ui/button"
import { DataTable } from "@/components/ui/data-tables"
import { useUsuario } from "@/contexts/user.context"
import { useQuery } from "@tanstack/react-query"
import { ColumnDef } from "@tanstack/react-table"
import { ArrowUpDown } from "lucide-react"
import { TrocaAlunoDTO } from "@/types/Troca/TrocaCreateRequestDTO"
import { AlunoRequisicao } from "@/server/Aluno"
import { AlunoResponse } from "@/types/Usuario/usuario.response"

export default function TabelaMinhasTrocas() {
  const { usuario } = useUsuario();

  const { data } = useQuery<TrocaAlunoDTO[]>({
    queryKey: ['trocas'],
    queryFn: async () => {
    if (!usuario) return [];
    const response = await AlunoRequisicao.BuscarMinhasTrocas((usuario as AlunoResponse).id);

    // Garante que sempre será um array
    if (!response.data) return [];
    return Array.isArray(response.data) ? response.data : [response.data];
  }
  })

  const columns: ColumnDef<TrocaAlunoDTO>[] = [
    {
      accessorKey: 'vantagem',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
            Vantagem
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.vantagem.descricao}</div>
    },
    {
      accessorKey: 'vantagem.custo',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
            Valor
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.vantagem.custo}</div>
    },
    {
      accessorKey: 'vantagem.empresaParceira.razaoSocial',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
            Empresa Parceira
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.vantagem.empresaParceira.razaoSocial}</div>
    },
    {
      accessorKey: 'status',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Status
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.status}</div>,
    },
    {
      accessorKey: 'dataHora',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Data/Hora
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{new Date(row.original.dataHora).toLocaleString("pt-BR", {
                                dateStyle: "short",
                                timeStyle: "short"
                              })}</div>,
    },
    // {
    //   id: "actions",
    //   enableHiding: false,
    //   cell: ({ row }) => (
    //     <DropdownMenu>
    //       <DropdownMenuTrigger asChild>
    //         <Button variant="ghost" className="h-8 w-8 !p-2">
    //           <BsThreeDots />
    //         </Button>
    //       </DropdownMenuTrigger>
    //       <DropdownMenuContent align="end">
    //         <DropdownMenuItem
    //           className="hover:cursor-pointer"
    //           onClick={(e) => {
    //             e.stopPropagation()
    //             handleEnviarMoedas(row.original)
    //           }}
    //         >
    //           Enviar moedas
    //         </DropdownMenuItem>
    //       </DropdownMenuContent>
    //     </DropdownMenu>
    //   ),
    // },
  ]

  return (
    <div className="flex h-auto w-auto mx-8 align-middle">
      <DataTable
        columnDef={columns}
        data={data || []}
        onClickRow={() => {}}
      />
    </div>
  )
}