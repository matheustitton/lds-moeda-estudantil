import { ResponseDto } from "@/types/response";
import { requisicaoBase } from "./requisicao.base";
import { AlunoResponse } from "@/types/Usuario/usuario.response";
import { TrocaCreateRequestDTO } from "@/types/Troca/TrocaCreateRequestDTO";

export class TrocaRequisicao {
    private static readonly ENDPOINT: string = "/api/trocas";

    static async trocar(troca: TrocaCreateRequestDTO): Promise<ResponseDto<void>> {        
      return requisicaoBase(`${this.ENDPOINT}`, "POST", undefined, troca);
    }
}
