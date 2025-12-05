'use client'

import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { z } from 'zod'
import { useQueryClient } from '@tanstack/react-query'
import { Form, FormField, FormItem, FormLabel, FormControl, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { Button } from '@/components/ui/button'
import api from '@/lib/axios'
import { toast } from 'sonner'

const empresaSchema = z.object({
  cnpj: z.string().min(1, { message: 'O CNPJ é obrigatório.' }),
  razaoSocial: z.string().min(1, { message: 'A razão social é obrigatória.' }),
  email: z.string().email({ message: 'Informe um e-mail válido.' }),
  senha: z.string().min(6, { message: 'A senha deve ter pelo menos 6 caracteres.' })
})

type FormData = z.infer<typeof empresaSchema>

export default function FormEmpresaParceira({ onVoltarLogin }: { onVoltarLogin: () => void }) {
  const form = useForm<FormData>({
    mode: 'onTouched',
    resolver: zodResolver(empresaSchema),
    defaultValues: {
      cnpj: '',
      razaoSocial: '',
      email: '',
      senha: ''
    }
  })

  const queryClient = useQueryClient()

  const handleSubmit = async (values: FormData) => {
    try {
      const response = await api.post('/api/empresas-parceiras', values)

      if (response.status === 201 || response.status === 200) {
        queryClient.invalidateQueries({ queryKey: ['empresas'] })
        toast.success("Cadastro realizado com sucesso! Agora você pode fazer login.");
        form.reset()
        onVoltarLogin()
      } else {
        toast.error("E-mail ou CNPJ já cadastrado.");
      }
    } catch (error) {
      console.error(error)
    }
  }

  return (
    <Form {...form}>
      <p className='text-gray-500 mb-6'>Preencha o formulário abaixo para criar sua conta de empresa parceira.</p>

      <form
        onSubmit={form.handleSubmit(handleSubmit)}
        className="flex flex-col gap-y-4 w-full"
      >
        <FormField
          control={form.control}
          name="cnpj"
          render={({ field }) => (
            <FormItem className="w-full">
              <FormLabel>CNPJ</FormLabel>
              <FormControl>
                <Input placeholder="Informe o CNPJ" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <FormField
          control={form.control}
          name="razaoSocial"
          render={({ field }) => (
            <FormItem className="w-full">
              <FormLabel>Razão Social</FormLabel>
              <FormControl>
                <Input placeholder="Informe a razão social" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <FormField
          control={form.control}
          name="email"
          render={({ field }) => (
            <FormItem className="w-full">
              <FormLabel>Email</FormLabel>
              <FormControl>
                <Input placeholder="Informe o e-mail" type="email" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <FormField
          control={form.control}
          name="senha"
          render={({ field }) => (
            <FormItem className="w-full">
              <FormLabel>Senha</FormLabel>
              <FormControl>
                <Input placeholder="Crie uma senha" type="password" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <Button
          type="submit"
          variant={"secondary"}
          className="w-full py-6 text-lg font-semibold bg-[#1E3A8A] hover:bg-[#7B1E3A] text-white rounded-xl transition-colors cursor-pointer"
        >
          Cadastrar
        </Button>

        <div className="flex w-full text-center justify-center py-3">
          <p
            className="text-sm underline text-[#7B1E3A] hover:text-[#1E3A8A] cursor-pointer"
            onClick={onVoltarLogin}
          >
            Já tem uma conta? Fazer login
          </p>
        </div>
      </form>
    </Form>
  )
}
