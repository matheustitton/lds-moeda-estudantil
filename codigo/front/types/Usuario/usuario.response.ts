import { InstituicaoEnsinoResponse } from "../Instituicao/instituicao.response"

export interface AlunoResponse {
    nome: string
    rg: string
    cpf: string
    curso: string
    saldo: number
    email: string
    instituicao: InstituicaoEnsinoResponse
}