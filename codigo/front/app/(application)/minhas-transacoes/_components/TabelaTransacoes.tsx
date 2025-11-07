'use client'
import { useState } from "react"
import { Button } from "@/components/ui/button"
import { DataTable } from "@/components/ui/data-tables"
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu"
import { useUsuario } from "@/contexts/user.context"
import { InstituicaoEnsinoRequisicao } from "@/server/InstituicaoEnsino"
import { MeritoProfessorDTO, ProfessorResponse } from "@/types/Professor/professor.response"
import { AlunoResponse } from "@/types/Usuario/usuario.response"
import { useQuery } from "@tanstack/react-query"
import { ColumnDef } from "@tanstack/react-table"
import { ArrowUpDown } from "lucide-react"
import { BsThreeDots } from "react-icons/bs"
import EnviarMoeda from "./ModalEnviarMoeda"
import ModalEnviarMoeda from "./ModalEnviarMoeda"
import { ProfessorRequisicao } from "@/server/Professor"

export default function TabelaTransacoes() {
  const { usuario } = useUsuario();

  const { data } = useQuery<MeritoProfessorDTO[]>({
    queryKey: ['transacoes'],
    queryFn: async () => {
      if (!usuario) return []
      const response = await ProfessorRequisicao.BuscarTransacoes((usuario as ProfessorResponse).id)
      return response.data ?? []
    }
  })

  const columns: ColumnDef<MeritoProfessorDTO>[] = [
    {
      accessorKey: 'data',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Data
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{new Date(row.original.data).toLocaleString("pt-BR", {
                                dateStyle: "short",
                                timeStyle: "short"
                              })}</div>,
    },
    {
      accessorKey: 'aluno',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Aluno
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.aluno.nome}</div>,
    },
    {
      accessorKey: 'valor',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Valor
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.valor}</div>,
    },
    {
      accessorKey: 'motivo',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Motivo
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <div>{row.original.motivo}</div>,
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