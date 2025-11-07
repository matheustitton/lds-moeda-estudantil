import { InstituicaoEnsinoResponse } from "../Instituicao/instituicao.response"
import { ITipoUsuario, TipoUsuario } from "./enum"

export interface AlunoResponse extends ITipoUsuario {
  id:number
  nome: string
  rg: string
  cpf: string
  curso: string
  saldo: number
  email: string
  instituicao: InstituicaoEnsinoResponse
  tipoUsuario: TipoUsuario.ALUNO
}

export interface UsuarioResponse {
    nome: string
    rg: string
    cpf: string
    curso: string
    saldo: number
    email: string
    instituicao: InstituicaoEnsinoResponse
}