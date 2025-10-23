import { ResponseDto } from "@/types/response";
import { requisicaoBase } from "./requisicao.base";

export class EmpresaRequisicao {

    private static readonly ENDPOINT: string = "/api/empresas-parceiras";

    static async Excluir(idEmpresa:number): Promise<ResponseDto<void>> {
        return requisicaoBase(`${this.ENDPOINT}/${idEmpresa}`, "DELETE");
    }
}