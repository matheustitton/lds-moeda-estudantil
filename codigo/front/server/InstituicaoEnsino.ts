import { ResponseDto } from "@/types/response";
import { AlunoResponse } from "@/types/Usuario/usuario.response";
import { requisicaoBase } from "./requisicao.base";


export class InstituicaoEnsinoRequisicao {

    private static readonly ENDPOINT: string = "/api/instituicoes-ensino";

    static async BuscarAlunos(instituicaoId: number): Promise<ResponseDto<AlunoResponse[]>> {
        return requisicaoBase(`${this.ENDPOINT}/${instituicaoId}/alunos`, "GET");
    }
}