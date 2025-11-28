'use client'

import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import api from "@/lib/axios";
import Sessao from "@/lib/utils/Sessao";
import { ChangeEvent, useEffect, useState } from "react";
import { useCallback } from "react";
import { useDropzone } from "react-dropzone";

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

    useEffect(() => {
    if (open && !vantagem) {
        setForm({
            descricao: "",
            custo: 0,
            tipo: "PRODUTO",
            idEmpresaParceira: 0
        });

        setImagem(null);
    }
}, [open, vantagem]);

    const [imagem, setImagem] = useState<File | null>(null);

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
            <DialogContent className="max-w-md w-full rounded-3xl p-6 bg-white shadow-2xl">
                <DialogHeader className="border-b border-gray-200 mb-6 pb-2">
                    <DialogTitle className="text-2xl font-bold text-primary">
                        {vantagem ? "Editar Vantagem" : "Cadastrar Vantagem"}
                    </DialogTitle>
                </DialogHeader>

                <div className="flex flex-col gap-4">
                    <Input
                        placeholder="Descrição"
                        name="descricao"
                        value={form.descricao}
                        onChange={handleChange}
                        className="text-primary border-gray-300 focus:ring-2 focus:ring-primary focus:border-transparent rounded-xl shadow-sm"
                    />

                    <Input
                        placeholder="Custo"
                        type="number"
                        name="custo"
                        value={form.custo}
                        onChange={handleChange}
                        className="border-gray-300 focus:ring-2 focus:ring-primary focus:border-transparent rounded-xl shadow-sm"
                    />

                    <select
                        name="tipo"
                        value={form.tipo}
                        onChange={handleChange}
                        className="border-gray-300 focus:ring-2 focus:ring-primary focus:border-transparent rounded-xl px-4 py-2 shadow-sm bg-white text-primary"
                    >
                        <option value="DESCONTO">DESCONTO</option>
                        <option value="PRODUTO">PRODUTO</option>
                    </select>

                    <select
                        name="idEmpresaParceira"
                        value={form.idEmpresaParceira}
                        onChange={handleChange}
                        className="border-gray-300 focus:ring-2 focus:ring-primary focus:border-transparent rounded-xl px-4 py-2 shadow-sm bg-white text-primary"
                    >
                        <option value={0}>Selecione uma empresa</option>
                        {(empresas || []).map(emp => (
                            <option key={emp.id} value={emp.id}>
                                {emp.razaoSocial}
                            </option>
                        ))}
                    </select>

                    <Dropzone imagem={imagem} setImagem={setImagem} />

                    <Button
                        onClick={() => mutation.mutate()}
                        className="bg-primary hover:bg-primary/90 text-white font-semibold rounded-2xl py-3 transition-all shadow-md"
                    >
                        {vantagem ? "Salvar Alterações" : "Cadastrar"}
                    </Button>
                </div>
            </DialogContent>
        </Dialog>
    );
}

function Dropzone({ imagem, setImagem }: { imagem: File | null, setImagem: (f: File | null) => void }) {
    const onDrop = useCallback((acceptedFiles: File[]) => {
        if (acceptedFiles && acceptedFiles[0]) {
            setImagem(acceptedFiles[0]);
        }
    }, [setImagem]);

    const { getRootProps, getInputProps, isDragActive } = useDropzone({
        onDrop,
        accept: { "image/*": [] }
    });

    return (
        <div
            {...getRootProps()}
            className={`
                border-2 border-dashed rounded-xl p-5 text-center cursor-pointer transition
                ${isDragActive ? "border-primary bg-primary/10" : "border-gray-300 bg-gray-50 hover:bg-gray-100"}
            `}
        >
            <input {...getInputProps()} />

            {imagem ? (
                <div className="flex flex-col items-center gap-2">
                    <img
                        src={URL.createObjectURL(imagem)}
                        alt="Preview"
                        className="w-24 h-24 object-cover rounded-lg shadow"
                    />
                    <span className="text-gray-700">{imagem.name}</span>
                </div>
            ) : (
                <p className="text-gray-600">
                    {isDragActive
                        ? "Solte a imagem aqui..."
                        : "Arraste e solte uma imagem aqui, ou clique para selecionar"}
                </p>
            )}
        </div>
    );
}
