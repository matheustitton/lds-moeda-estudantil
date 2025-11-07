import { ResponseDto } from "@/types/response";
import { requisicaoBase } from "./requisicao.base";
import { AlunoResponse } from "@/types/Usuario/usuario.response";
import { ProfessorResponse } from "@/types/Professor/professor.response";

export class ProfessorRequisicao {

    private static readonly ENDPOINT: string = "/api/professores";

    static async Excluir(idAluno:number): Promise<ResponseDto<void>> {
        console.log(idAluno)
        return requisicaoBase(`${this.ENDPOINT}/${idAluno}`, "DELETE");
    }

    static async GetById(professorId: number): Promise<ResponseDto<ProfessorResponse>> {
        return requisicaoBase(`${this.ENDPOINT}/${professorId}`, "GET");
    }
}

export interface AlunoUpdateRequest{
    nome: string,
    curso: string,
    instituicaoEnsino: number,
    email: string,
}