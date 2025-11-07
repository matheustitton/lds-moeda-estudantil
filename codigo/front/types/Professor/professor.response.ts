import { InstituicaoEnsinoResponse } from "../Instituicao/instituicao.response"
import { ITipoUsuario, TipoUsuario } from "../Usuario/enum"
import { AlunoResponse } from "../Usuario/usuario.response"

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
export interface MeritoProfessorDTO {
  id: number
  valor: number
  motivo: string
  aluno: AlunoResponse
  data: Date
}