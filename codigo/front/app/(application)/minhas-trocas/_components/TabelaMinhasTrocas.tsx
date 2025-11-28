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

function formatarStatus(status: string) {
  switch (status) {
    case "AGUARDANDO_RESGATE":
      return "Aguardando resgate";
    case "CONCLUIDA":
      return "Concluída";
    case "CANCELADA":
      return "Cancelada";
    default:
      return status;
  }
}

function BadgeStatus({ status }: { status: string }) {
  const mapaCores: Record<string, string> = {
    AGUARDANDO_RESGATE: "bg-yellow-200 text-yellow-800",
    CONCLUIDA: "bg-green-200 text-green-800",
    CANCELADA: "bg-red-200 text-red-800"
  };

  return (
    <span
      className={`px-3 py-1 rounded-full text-xs font-semibold ${
        mapaCores[status] ?? "bg-gray-200 text-gray-700"
      }`}
    >
      {formatarStatus(status)}
    </span>
  );
}

export default function TabelaMinhasTrocas() {
  const { usuario } = useUsuario();

  const { data } = useQuery<TrocaAlunoDTO[]>({
    queryKey: ['trocas'],
    queryFn: async () => {
      if (!usuario) return [];
      const response = await AlunoRequisicao.BuscarMinhasTrocas((usuario as AlunoResponse).id);

      if (!response.data) return [];
      return Array.isArray(response.data) ? response.data : [response.data];
    }
  });

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

    // ----------------------------
    // AQUI ESTÁ A COLUNA MODIFICADA COM BADGE
    // ----------------------------
    {
      accessorKey: 'status',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Status
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => <BadgeStatus status={row.original.status} />
    },

    {
      accessorKey: 'dataHora',
      header: ({ column }) => (
        <Button variant="ghost" className="!p-0" onClick={() => column.toggleSorting(column.getIsSorted() === 'asc')}>
          Data/Hora
          <ArrowUpDown />
        </Button>
      ),
      cell: ({ row }) => (
        <div>
          {new Date(row.original.dataHora).toLocaleString("pt-BR", {
            dateStyle: "short",
            timeStyle: "short"
          })}
        </div>
      ),
    },
  ];

  return (
    <div className="flex h-auto w-auto mx-8 align-middle">
      <DataTable
        columnDef={columns}
        data={data || []}
        onClickRow={() => {}}
      />
    </div>
  );
}
