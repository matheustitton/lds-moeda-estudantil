"use client";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "@/components/ui/form";
import { z } from "zod";
import { Input } from "@/components/ui/input";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod/dist/zod.js";
import Image from "next/image";
import { LoginRequisicao } from "@/server/Login";
import { Utils } from "@/lib/utils/utils";
import { useRouter } from "next/navigation";
import { toast } from "sonner";
import { useState } from "react";
import AlunoForm from "@/app/alunos/_components/AlunoForm";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card";
import { Label } from "@/components/ui/label";
import EmpresaForm from "@/app/empresas/_components/EmpresaForm";

const loginSchema = z.object({
  email: z.string().min(1, { message: "Informe o e-mail." }),
  senha: z.string().min(1, { message: "Informe a senha." }),
});

type FormData = z.infer<typeof loginSchema>;

export default function Login() {
  const { buscarTokenAcesso, limparTodos } = Utils.Sessao;
  const [isLoginForm, setIsLoginForm] = useState(true);

  const router = useRouter()

  const form = useForm<FormData>({
    mode: "onTouched",
    resolver: zodResolver(loginSchema),
    defaultValues: {
      email: "",
      senha: "",
    },
  });

  const handleSubmit = async (values: FormData) => {
    const token = buscarTokenAcesso();
    if (token) limparTodos();

    try {
      const response = await LoginRequisicao.Login(values);

      if (response.statusCode === 201 || response.statusCode === 200) {
        router.push("/home")
        toast.success("Login realizado com sucesso!");
        form.reset();
      }
    } catch (error) {
      console.error(error);
      toast.error("Erro ao realizar login.");
    }
  };

  return (
    <div className="flex h-screen bg-white">
      {/* Área do formulário */}
      <div className="flex w-full md:w-2/5 items-center justify-center p-10 bg-white shadow-lg">
        <div className="flex flex-col w-full max-w-md bg-white p-8 rounded-2xl border border-gray-100">

          {/* <div className="items-center justify-center">
            <Image
              src="/logo.png"
              alt="Logo da Aplicação"
              width={150}
              height={150}
              className="mx-auto mb-4 border-2 border-gray-100 rounded-full"
            />
          </div> */}

          <h1 className="text-4xl font-bold mb-8 text-center text-[#1E3A8A]">
            Bem-vindo
          </h1>
          {isLoginForm ? (
            <Form {...form}>
              <form
                onSubmit={form.handleSubmit(handleSubmit)}
                className="flex flex-col gap-y-4 w-full"
              >
                <FormField
                  control={form.control}
                  name="email"
                  render={({ field }) => (
                    <FormItem>
                      <FormControl>
                        <Input
                          value={String(field.value)}
                          onChange={field.onChange}
                          placeholder="Email"
                          className="h-12 text-lg border-2 border-gray-200 focus:border-[#1E3A8A] focus:ring-[#1E3A8A] rounded-xl"
                        />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <FormField
                  control={form.control}
                  name="senha"
                  render={({ field }) => (
                    <FormItem>
                      <FormControl>
                        <Input
                          value={String(field.value)}
                          onChange={field.onChange}
                          type="password"
                          placeholder="Senha"
                          className="h-12 text-lg border-2 border-gray-200 focus:border-[#1E3A8A] focus:ring-[#1E3A8A] rounded-xl"
                        />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />

                <div className="flex w-full justify-end">
                  <p className="text-sm underline text-[#7B1E3A] hover:text-[#1E3A8A] cursor-pointer">
                    Esqueceu a senha?
                  </p>
                </div>

                <Button
                  type="submit"
                  variant={"secondary"}
                  className="w-full py-6 text-lg font-semibold bg-[#1E3A8A] hover:bg-[#7B1E3A] text-white rounded-xl transition-colors cursor-pointer"
                >
                  Entrar
                </Button>

                <div className="flex w-full text-center justify-center py-3">
                  <p
                    className="text-sm underline text-[#7B1E3A] hover:text-[#1E3A8A] cursor-pointer"
                    onClick={() => setIsLoginForm(false)}
                  >
                    Não tem uma conta? Cadastre-se
                  </p>
                </div>
              </form>
            </Form>
          ) : (
            <Tabs defaultValue="aluno">
              <TabsList>
                <TabsTrigger value="aluno">Alunos</TabsTrigger>
                <TabsTrigger value="empresa">Empresas</TabsTrigger>
              </TabsList>
              <TabsContent value="aluno">
                <AlunoForm onVoltarLogin={() => setIsLoginForm(true)} />
              </TabsContent>
              <TabsContent value="empresa">
                <EmpresaForm onVoltarLogin={() => setIsLoginForm(true)} />
              </TabsContent>
            </Tabs>
          )}
        </div>
      </div>

      {/* Área da imagem */}
      <div className="hidden md:flex w-3/5 items-center justify-center overflow-hidden relative bg-[#1E3A8A]">
        <div className="absolute inset-0 bg-[#1E3A8A]/40 z-10" />
        <Image
          src="/wp.png"
          alt="Ilustração de login"
          fill
          className="object-cover opacity-100"
          priority
        />
        <div className="absolute z-20 text-white text-center px-10">
          <h2 className="text-3xl font-semibold mb-4">
            EducaCoins
          </h2>
          <p className="text-lg text-gray-100">
            Reconhecendo o mérito de quem se dedica: cada conquista vale uma recompensa.
          </p>
        </div>
      </div>
    </div>
  );
}
