"use client";

import { useState } from "react";
import axios from "axios";
import Image from "next/image";
import { Button } from "@/components/ui/button";

interface Props {
    vantagemId: number;
    descricao: string;
    imagemUrl: string;
}

export default function ResgatarVantagemPage({ vantagemId, descricao, imagemUrl }: Props) {
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    async function handleResgatar() {
        try {
            setLoading(true);
            setMsg("");

            const response = await axios.post(
                `http://48.217.82.183:8080/api/vantagens/${vantagemId}/resgatar`,
                {},
                { withCredentials: true }
            );

            setMsg(response.data || "Código enviado para seu e-mail!");
        } catch (error: any) {
            setMsg(error.response?.data || "Erro ao resgatar vantagem.");
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="flex flex-col items-center max-w-lg mx-auto p-6 gap-6">

            <h1 className="text-2xl font-bold text-primary">Resgatar Vantagem</h1>

            <div className="w-full rounded-2xl overflow-hidden shadow-lg border border-gray-300">
                <Image
                    src={imagemUrl}
                    width={600}
                    height={400}
                    alt="Imagem da vantagem"
                    className="object-cover w-full h-64"
                />
            </div>

            <p className="text-center text-lg text-gray-700">{descricao}</p>

            <Button
                className="w-full h-12 text-lg bg-primary hover:bg-primary/80"
                onClick={handleResgatar}
                disabled={loading}
            >
                {loading ? "Enviando código..." : "Resgatar agora"}
            </Button>

            {msg && (
                <p className="text-center text-secondary font-semibold">{msg}</p>
            )}
        </div>
    );
}
