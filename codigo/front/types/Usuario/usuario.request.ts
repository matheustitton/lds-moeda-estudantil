import { TipoUsuario } from "./enum";

export interface UsuarioRequest {
  nome: string;
  rg: string;
  cpf: string;
  curso: string;
  email: string;
  senha: string;
  tipo: TipoUsuario;
  instituicaoEnsino?: number;
}
