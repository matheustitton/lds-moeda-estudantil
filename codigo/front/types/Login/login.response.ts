export interface LoginResponse {
  tokenAcesso: {
    token: string;
    expiraEm: number;
  };
}