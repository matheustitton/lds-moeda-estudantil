'use client'

import { useQuery, useQueryClient } from "@tanstack/react-query";
import { Button } from "@/components/ui/button";
import api from "@/lib/axios";
import Sessao from "@/lib/utils/Sessao";
import { BsThreeDots, BsPlus } from "react-icons/bs";
import {
    DropdownMenu,
    DropdownMenuTrigger,
    DropdownMenuContent,
    DropdownMenuItem
} from "@/components/ui/dropdown-menu";
import { useState } from "react";
import FormVantagem from "./ModalFormVantagem";
import { Skeleton } from "@/components/ui/skeleton";

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

    const { data: vantagens, isLoading } = useQuery<VantagemDTO[]>({
        queryKey: ["vantagens"],
        queryFn: async () => {
            const response = await api.get("/api/vantagens", {
                headers: { Authorization: `Bearer ${Sessao.buscarTokenAcesso()}` }
            });
            return response.data;
        }
    });

    async function excluirVantagem(id: number) {
        try {
            await api.delete(`/api/vantagens/${id}`, {
                headers: { Authorization: `Bearer ${Sessao.buscarTokenAcesso()}` }
            });
            queryClient.invalidateQueries({ queryKey: ["vantagens"] });
        } catch (err) {
            console.error("Erro ao excluir vantagem:", err);
        }
    }

    return (
        <div className="relative">
            {/* Botão flutuante para cadastro */}
            <Button
                onClick={() => {
                    setVantagemEdit(null);
                    setOpenModal(true);
                }}
                className="fixed bottom-8 right-8 rounded-full w-16 h-16 flex items-center justify-center shadow-xl bg-primary text-white text-2xl hover:bg-primary/90 transition z-50"
            >
                <BsPlus />
            </Button>

            {isLoading ? (
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                    {Array.from({ length: 6 }).map((_, i) => (
                        <Skeleton key={i} className="h-64 w-full rounded-2xl" />
                    ))}
                </div>
            ) : (
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                    {(vantagens || []).map(vant => (
                        <div
                            key={vant.id}
                            className="border rounded-2xl shadow-lg p-5 bg-white hover:shadow-2xl transition hover:scale-[1.02] flex flex-col gap-3"
                        >
                            {/* Imagem */}
                            <div className="w-full h-36 bg-gray-100 rounded-xl overflow-hidden flex items-center justify-center">
                                <img
                                    src={`/imagens-proxy${vant.imagemUrl || "/placeholder-image.png"}`}
                                    alt={vant.descricao}
                                    className="object-cover w-full h-full"
                                    loading="lazy"
                                />
                            </div>

                            {/* Descrição + menu */}
                            <div className="flex justify-between items-start">
                                <h2 className="text-lg font-semibold text-gray-800 max-w-[75%]">
                                    {vant.descricao}
                                </h2>

                                <DropdownMenu>
                                    <DropdownMenuTrigger asChild>
                                        <Button variant="ghost" className="h-6 w-6 p-0">
                                            <BsThreeDots />
                                        </Button>
                                    </DropdownMenuTrigger>
                                    <DropdownMenuContent align="end">
                                        <DropdownMenuItem
                                            onClick={() => {
                                                setVantagemEdit(vant);
                                                setOpenModal(true);
                                            }}
                                        >
                                            Editar
                                        </DropdownMenuItem>

                                        <DropdownMenuItem
                                            className="text-red-600 focus:text-red-600"
                                            onClick={() => excluirVantagem(vant.id)}
                                        >
                                            Excluir
                                        </DropdownMenuItem>
                                    </DropdownMenuContent>
                                </DropdownMenu>
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
                        </div>
                    ))}
                </div>
            )}

            <FormVantagem
                open={openModal}
                onClose={() => {
                    setOpenModal(false);
                    setVantagemEdit(null);
                }}
                vantagem={vantagemEdit}
            />
        </div>
    );
}
