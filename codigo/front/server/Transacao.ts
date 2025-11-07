import { ResponseDto } from "@/types/response";
import { AlunoResponse } from "@/types/Usuario/usuario.response";
import { requisicaoBase } from "./requisicao.base";
import { TransacaoRequestDTO } from "@/types/Transacao/transacao.request";


export class TransacaoRequisicao {

    private static readonly ENDPOINT: string = "/api/meritos";

    static async EnviarMoedas(transacao: TransacaoRequestDTO): Promise<ResponseDto<AlunoResponse[]>> {
        
        return requisicaoBase(`${this.ENDPOINT}`, "POST", undefined, transacao);
    }
}