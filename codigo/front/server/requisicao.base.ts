import { AxiosRequestConfig, Method } from "axios";
import { Utils } from "@/lib/utils/utils";
import { ResponseDto } from "@/types/response";
import api from "@/lib/axios";

const BASE_PATH = "http://48.217.82.183:8080";

export async function requisicaoBase<T, E>(
  endpoint: string,
  method: Method,
  params?: Record<string, string>,
  body?: T
): Promise<ResponseDto<E>> {
  const url = `${BASE_PATH}${endpoint}`;

  const headers: Record<string, string> = {
    "Content-Type": "application/json",
    Accept: "application/json",
  };

  const tokenAcesso = Utils.Sessao.buscarTokenAcesso();
  if (tokenAcesso) {
    headers["Authorization"] = `Bearer ${tokenAcesso}`;
  }

  try {
    const response = await api({
      url,
      method,
      headers,
      withCredentials: true,
      params,
      data: body,
    });

    return { data: response.data, error: null, statusCode: response.status };
  } catch (error: any) {
    const status = error.response?.status || 500;

    if (status === 401) {
      const renovado = await renovarToken();

      if (renovado) {
        const novoTokenAcesso = Utils.Sessao.buscarTokenAcesso();

        if (novoTokenAcesso) {
          headers["Authorization"] = `Bearer ${novoTokenAcesso}`;
          const retryResponse = await api({
            url,
            method,
            headers,
            withCredentials: true,
            params,
            data: body,
          });
          return { data: retryResponse.data, error: null, statusCode: retryResponse.status };
        }
      }

      Utils.Sessao.limparTodos();
      return { data: null, error: "Sessão expirada. Faça login novamente.", statusCode: 401 };
    }

    return { data: null, error: error.response?.data || error.message, statusCode: error.response?.status || 500 };
  }
}

async function renovarToken(): Promise<boolean> {
  const tokenRenovacao = Utils.Sessao.buscarTokenRenovacao();
  if (!tokenRenovacao) return false;

  try {
    const response = await api.post(`${BASE_PATH}/api/token`, {
      tokenRenovacao: tokenRenovacao,
    });

    const novoTokenAcesso = response.data?.token;
    const expiraEm = response.data?.expiraEm;
    if (!novoTokenAcesso) return false;

    Utils.Sessao.definirTokenAcesso(novoTokenAcesso, expiraEm);
    return true;
  } catch {
    return false;
  }
}
