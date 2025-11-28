'use client'

import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import api from "@/lib/axios";
import Sessao from "@/lib/utils/Sessao";
import { ChangeEvent, useEffect, useState } from "react";

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

interface Props {
    open: boolean;
    onClose: () => void;
    vantagem?: VantagemDTO | null;
}

export default function ModalFormVantagem({ open, onClose, vantagem }: Props) {

    const queryClient = useQueryClient();

    const [form, setForm] = useState({
        descricao: "",
        custo: 0,
        tipo: "PRODUTO",
        idEmpresaParceira: 0
    });

    const [imagem, setImagem] = useState<File | null>(null);

    // Busca empresas
    const { data: empresas } = useQuery<EmpresaParceiraDTO[]>({
        queryKey: ["empresas"],
        queryFn: async () => {
            const token = Sessao.buscarTokenAcesso();
            const resp = await api.get("/api/empresas-parceiras", {
                headers: { Authorization: `Bearer ${token}` }
            });
            return resp.data;
        }
    });

    useEffect(() => {
        if (vantagem) {
            setForm({
                descricao: vantagem.descricao,
                custo: vantagem.custo,
                tipo: vantagem.tipo,
                idEmpresaParceira: vantagem.empresaParceira?.id || 0
            });
        }
    }, [vantagem]);

    const mutation = useMutation({
        mutationFn: async () => {
            const token = Sessao.buscarTokenAcesso();

            const formData = new FormData();
            formData.append("dto", new Blob([JSON.stringify(form)], { type: "application/json" }));
            if (imagem) formData.append("imagem", imagem);

            if (vantagem) {
                await api.put(`/api/vantagens/${vantagem.id}`, formData, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "multipart/form-data"
                    }
                });
            } else {
                await api.post("/api/vantagens", formData, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "multipart/form-data"
                    }
                });
            }
        },
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ["vantagens"] });
            onClose();
            setImagem(null);
        },
        onError: (err: any) => {
            console.error("Erro ao salvar vantagem:", err.response?.data || err);
            alert("Erro ao salvar vantagem. Verifique os campos e autenticação.");
        }
    });

    function handleChange(e: ChangeEvent<any>) {
        const { name, value } = e.target;
        setForm(prev => ({
            ...prev,
            [name]: name === "custo" || name === "idEmpresaParceira" ? Number(value) : value
        }));
    }

    function handleImagemChange(e: ChangeEvent<HTMLInputElement>) {
        if (e.target.files && e.target.files[0]) {
            setImagem(e.target.files[0]);
        }
    }

    return (
        <Dialog open={open} onOpenChange={onClose}>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>{vantagem ? "Editar Vantagem" : "Cadastrar Vantagem"}</DialogTitle>
                </DialogHeader>

                <div className="flex flex-col gap-4">
                    <Input
                        placeholder="Descrição"
                        name="descricao"
                        value={form.descricao}
                        onChange={handleChange}
                    />

                    <Input
                        placeholder="Custo"
                        type="number"
                        name="custo"
                        value={form.custo}
                        onChange={handleChange}
                    />

                    <select
                        name="tipo"
                        value={form.tipo}
                        onChange={handleChange}
                        className="border px-3 py-2 rounded-md"
                    >
                        <option value="DESCONTO">DESCONTO</option>
                        <option value="PRODUTO">PRODUTO</option>
                    </select>

                    <select
                        name="idEmpresaParceira"
                        value={form.idEmpresaParceira}
                        onChange={handleChange}
                        className="border px-3 py-2 rounded-md"
                    >
                        <option value={0}>Selecione uma empresa</option>
                        {(empresas || []).map(emp => (
                            <option key={emp.id} value={emp.id}>
                                {emp.razaoSocial}
                            </option>
                        ))}
                    </select>

                    {/* Upload de imagem */}
                    <input
                        type="file"
                        accept="image/*"
                        onChange={handleImagemChange}
                        className="border px-3 py-2 rounded-md"
                    />

                    <Button onClick={() => mutation.mutate()}>
                        {vantagem ? "Salvar Alterações" : "Cadastrar"}
                    </Button>
                </div>
            </DialogContent>
        </Dialog>
    );
}
