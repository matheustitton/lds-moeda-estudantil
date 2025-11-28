import { ResponseDto } from "@/types/response";
import { requisicaoBase } from "./requisicao.base";
import { AlunoResponse } from "@/types/Usuario/usuario.response";
import { TrocaAlunoDTO } from "@/types/Troca/TrocaCreateRequestDTO";

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

    static async Editar(alunoId: number, aluno: AlunoUpdateRequest): Promise<ResponseDto<AlunoResponse>> {
        return requisicaoBase(`${this.ENDPOINT}/${alunoId}/id=${alunoId}`, "PUT", undefined, aluno);
    }

    static async BuscarMinhasTrocas(alunoId: number): Promise<ResponseDto<TrocaAlunoDTO>> {
        return requisicaoBase(`${this.ENDPOINT}/${alunoId}/trocas`, "GET", undefined);    
    }
}

export interface AlunoUpdateRequest{
    nome: string,
    curso: string,
    instituicaoEnsino: number,
    email: string,
}