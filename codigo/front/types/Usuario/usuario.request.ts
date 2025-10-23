export enum TipoUsuario {
  ALUNO = 'ALUNO',
  PROFESSOR = 'PROFESSOR',
  EMPRESA_PARCEIRA = 'EMPRESA_PARCEIRA'
}

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
