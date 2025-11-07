import { Button } from "@/components/ui/button";
import FormEmpresaParceira from "../login/_components/EmpresaForm";
import TabelaEmpresas from "./_components/TabelaEmpresa";


export default function Usuarios() {
  return (
    <div className="font-sans grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20">
      <main className="flex flex-col gap-[32px] row-start-2 items-center justify-center sm:items-start">
        {/* //<FormEmpresaParceira/> */}
        <TabelaEmpresas/>
      </main>
      
    </div>
  );
}
