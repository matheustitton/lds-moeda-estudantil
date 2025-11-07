import { InstituicaoEnsinoResponse } from "../Instituicao/instituicao.response"
import { ITipoUsuario, TipoUsuario } from "../Usuario/enum"

export interface ProfessorResponse extends ITipoUsuario {
  id: number
  nome: string
  departamento: string
  cpf: string
  instituicao: InstituicaoEnsinoResponse
  saldo: number
  email: string
  tipoUsuario: TipoUsuario.PROFESSOR
}