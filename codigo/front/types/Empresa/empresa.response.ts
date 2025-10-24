import { ITipoUsuario, TipoUsuario } from "../Usuario/enum";

export interface EmpresaParceira extends ITipoUsuario {
    id: number, 
    cnpj: string, 
    razaoSocial: string
    tipoUsuario: TipoUsuario.EMPRESA_PARCEIRA
}