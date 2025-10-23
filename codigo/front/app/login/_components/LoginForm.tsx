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

const loginSchema = z.object({
  email: z.string().min(1, { message: "Informe o e-mail." }),
  senha: z.string().min(1, { message: "Informe a senha." }),
});

type FormData = z.infer<typeof loginSchema>;

export default function Login() {
  const form = useForm<FormData>({
    mode: "onTouched",
    resolver: zodResolver(loginSchema),
    defaultValues: {
      email: "",
      senha: "",
    },
  });

  const handleSubmit = async (values: FormData) => {
    try {
      const response = await LoginRequisicao.Login(values);

      if (response.statusCode === 201 || response.statusCode === 200) {
        alert('Login realizado com sucesso!')
        form.reset()
      }
    } catch (error) {
      console.error(error)
      alert('Erro ao realizar login.')
    }
  };

  return (
    <div className="flex h-screen">
      {/* Área do formulário */}
      <div className="flex w-full md:w-2/5 items-center justify-center p-10">
        <div className="flex flex-col w-full max-w-md">
          <h1 className="text-4xl font-bold mb-8 text-center">Bem-vindo</h1>
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
                        className="h-12 text-lg"
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
                        className="h-12 text-lg"
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />

              <div className="flex w-full justify-end">
                <p className="text-sm underline text-gray-600 hover:text-gray-800 cursor-pointer">
                  Esqueceu a senha?
                </p>
              </div>

              <Button
                type="submit"
                variant={"secondary"}
                className="w-full py-6 text-lg font-semibold"
              >
                Entrar
              </Button>
            </form>
          </Form>
        </div>
      </div>

      {/* Área da imagem */}
      <div className="hidden md:flex w-3/5 items-center justify-center overflow-hidden relative">
        <Image
          src="/wp.png"
          alt="Ilustração de login"
          fill
          className="object-cover"
          priority
        />
      </div>
    </div>
  );
}
