'use client'

import { useQuery, useQueryClient } from "@tanstack/react-query";
import { Button } from "@/components/ui/button";
import api from "@/lib/axios";
import Sessao from "@/lib/utils/Sessao";
import { BsThreeDots } from "react-icons/bs";
import {
    DropdownMenu,
    DropdownMenuTrigger,
    DropdownMenuContent,
    DropdownMenuItem
} from "@/components/ui/dropdown-menu";
import { useState } from "react";
import FormVantagem from "./ModalFormVantagem";

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

    const { data: vantagens } = useQuery<VantagemDTO[]>({
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
        <div className="w-full p-6 bg-gray-50 rounded-lg shadow-md">
            <div className="flex justify-begin mb-6">
                <Button
                    onClick={() => {
                        setVantagemEdit(null);
                        setOpenModal(true);
                    }}
                >
                    Cadastrar Vantagem
                </Button>
            </div>

            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                {(vantagens || []).map(vant => (
                    <div
                        key={vant.id}
                        className="border rounded-xl shadow-sm p-5 bg-white hover:shadow-md transition"
                    >
                        {/* ESPAÇO PARA IMAGEM */}
                        <div className="w-full h-32 bg-gray-200 rounded-md mb-3 flex items-center justify-center overflow-hidden">
                            <img
                                src={`http://localhost:8080${vant.imagemUrl || "/placeholder-image.png"}`}
                                alt={vant.descricao}
                                className="object-cover w-full h-full"
                            />
                        </div>

                        <div className="flex justify-between mb-2">
                            <h2 className="text-lg font-semibold max-w-[80%]">
                                {vant.descricao}
                            </h2>

                            <DropdownMenu>
                                <DropdownMenuTrigger>
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

                        <p className="text-gray-600 mb-3">
                            Empresa: <strong>{vant.empresaParceira?.razaoSocial || "—"}</strong>
                        </p>

                        <p className="text-gray-800 font-semibold mb-1">
                            Custo: {vant.custo} moedas
                        </p>

                        <span className="text-sm px-3 py-1 rounded-full bg-blue-100 text-blue-800">
                            {vant.tipo}
                        </span>
                    </div>
                ))}
            </div>
            
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
