import { LoginResponse } from "@/types/Login/login.response";
import { ResponseDto } from "@/types/response";
import { requisicaoBase } from "./requisicao.base";
import { LoginRequest } from "@/types/Login/login.request";
import { Utils } from "@/lib/utils/utils";

export class LoginRequisicao {

  private static readonly ENDPOINT: string = "/api/login";

  static async Login(data: LoginRequest): Promise<ResponseDto<LoginResponse>> {
    const { definirTokenAcesso } = Utils.Sessao

    try {
      const body: LoginRequest = { ...data };

      const response = await requisicaoBase<LoginRequest, LoginResponse>(
        this.ENDPOINT,
        "POST",
        undefined,
        body
      );

      if (response.error || !response.data) {
        return {
          data: response.data,
          error: response.error ?? "Dados de resposta não encontrados",
          statusCode: response.statusCode,
        };
      }

      const { tokenAcesso } = response.data;
      definirTokenAcesso(tokenAcesso.token, tokenAcesso.expiraEm);

      return {
        data: response.data,
        error: null,
        statusCode: response.statusCode,
      };
    } catch (error: any) {
      return {
        data: null,
        error: error.message || "Erro desconhecido",
        statusCode: error.statusCode || 500,
      };
    }
  }
}