export enum TipoUsuario {
  ALUNO = 'ALUNO',
  PROFESSOR = 'PROFESSOR',
  EMPRESA_PARCEIRA = 'EMPRESA_PARCEIRA'
}

export interface ITipoUsuario {
    tipoUsuario: TipoUsuario
}