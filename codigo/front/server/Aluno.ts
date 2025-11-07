import { ResponseDto } from "@/types/response";
import { requisicaoBase } from "./requisicao.base";
import { AlunoResponse } from "@/types/Usuario/usuario.response";

export class AlunoRequisicao {

    private static readonly ENDPOINT: string = "/api/alunos";

    static async Excluir(idAluno:number): Promise<ResponseDto<void>> {
        return requisicaoBase(`${this.ENDPOINT}/${idAluno}`, "DELETE");
    }

    static async BuscarTodos(): Promise<ResponseDto<AlunoResponse[]>> {
        return requisicaoBase(`${this.ENDPOINT}`, "GET");
    }

    static async GetById(alunoId: number): Promise<ResponseDto<AlunoResponse>> {
        return requisicaoBase(`${this.ENDPOINT}?id=${alunoId}`, "GET");
    }
}