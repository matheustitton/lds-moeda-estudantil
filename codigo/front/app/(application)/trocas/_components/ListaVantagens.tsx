'use client'

import { useQuery, useQueryClient } from "@tanstack/react-query";
import { Button } from "@/components/ui/button";
import api from "@/lib/axios";
import Sessao from "@/lib/utils/Sessao";
import { useUsuario } from "@/contexts/user.context";
import { BsPlus } from "react-icons/bs";
import { useState } from "react";
import { Skeleton } from "@/components/ui/skeleton";
import { TipoUsuario } from "@/types/Usuario/enum";
import { AlunoResponse } from "@/types/Usuario/usuario.response";
import { Utils } from "@/lib/utils/utils";
import { TrocaRequisicao } from "@/server/Troca";
import { toast } from "sonner";

interface EmpresaParceiraDTO {
  id: number;
  razaoSocial: string;
}

interface VantagemDTO {
  id: number;
  descricao: string;
  custo: number;
  tipo: string;
  empresaParceira: EmpresaParceiraDTO | null;
  imagemUrl?: string;
}

export default function ListaVantagens() {
  const [openModal, setOpenModal] = useState(false);
  const [vantagemEdit, setVantagemEdit] = useState<VantagemDTO | null>(null);
  const queryClient = useQueryClient();

  const decoded = Utils.Sessao.decodificarToken()
  const { usuario } = useUsuario();
  const aluno = decoded?.tipo == TipoUsuario.ALUNO ? (usuario as AlunoResponse) : null;

  const { data: vantagens, isLoading } = useQuery<VantagemDTO[]>({
    queryKey: ["vantagens"],
    queryFn: async () => {
      const response = await api.get("/api/vantagens", {
        headers: { Authorization: `Bearer ${Sessao.buscarTokenAcesso()}` }
      });
      return response.data;
    }
  });

  async function comprarVantagem(v: VantagemDTO) {
     if (!aluno) return;

    try {
        await TrocaRequisicao.trocar({
            idAluno: aluno.id,
            idVantagem: v.id,
        });

        toast.success("Compra realizada com sucesso.");
    } catch (error) {
        console.error("Erro ao comprar:", error);
    }
  }

  return (
    <div className="relative">
      {isLoading ? (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {Array.from({ length: 6 }).map((_, i) => (
            <Skeleton key={i} className="h-64 w-full rounded-2xl" />
          ))}
        </div>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {(vantagens || []).map(vant => {
            const saldoInsuficiente = aluno ? aluno.saldo < vant.custo : false;

            return (
              <div
                key={vant.id}
                className="border rounded-2xl shadow-lg p-5 bg-white hover:shadow-2xl transition hover:scale-[1.02] flex flex-col gap-3"
              >
                {/* Imagem */}
                <div className="w-full h-36 bg-gray-100 rounded-xl overflow-hidden flex items-center justify-center">
                  <img
                    src={`http://48.217.82.183:8080${vant.imagemUrl || "/placeholder-image.png"}`}
                    alt={vant.descricao}
                    className="object-cover w-full h-full"
                    loading="lazy"
                  />
                </div>

                {/* Descrição */}
                <div className="flex justify-between items-start">
                  <h2 className="text-lg font-semibold text-gray-800 max-w-[75%]">
                    {vant.descricao}
                  </h2>
                </div>

                <p className="text-gray-600">
                  Empresa: <strong>{vant.empresaParceira?.razaoSocial || "—"}</strong>
                </p>

                <div className="flex justify-between items-center">
                  <span className="text-sm font-semibold px-3 py-1 rounded-full bg-primary/10 text-primary">
                    {vant.tipo}
                  </span>
                  <span className="text-sm font-semibold px-3 py-1 rounded-full bg-secondary/10 text-secondary">
                    Custo: {vant.custo} moedas
                  </span>
                </div>

                <Button
                  className="w-full mt-3"
                  disabled={saldoInsuficiente}
                  onClick={() => comprarVantagem(vant)}
                >
                  {saldoInsuficiente ? "Saldo insuficiente" : "Comprar"}
                </Button>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
}
