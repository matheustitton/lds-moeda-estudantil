import { ResponseDto } from "@/types/response";
import { requisicaoBase } from "./requisicao.base";
import { AlunoResponse } from "@/types/Usuario/usuario.response";
import { MeritoProfessorDTO, ProfessorResponse } from "@/types/Professor/professor.response";

export class ProfessorRequisicao {

    private static readonly ENDPOINT: string = "/api/professores";

    static async BuscarTransacoes(professorId:number): Promise<ResponseDto<MeritoProfessorDTO[]>> {
        return requisicaoBase(`${this.ENDPOINT}/${professorId}/meritos`, "GET");
    }

    static async GetById(professorId: number): Promise<ResponseDto<ProfessorResponse>> {
        return requisicaoBase(`${this.ENDPOINT}/${professorId}`, "GET");
    }
}