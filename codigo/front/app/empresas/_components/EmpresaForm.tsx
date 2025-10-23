'use client'

import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { z } from 'zod'
import { useQueryClient } from '@tanstack/react-query'
import { Form, FormField, FormItem, FormLabel, FormControl, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { Button } from '@/components/ui/button'
import api from '@/lib/axios'

const empresaSchema = z.object({
  cnpj: z.string().min(1, { message: 'O CNPJ é obrigatório.' }),
  razaoSocial: z.string().min(1, { message: 'A razão social é obrigatória.' }),
  email: z.string().email({ message: 'Informe um e-mail válido.' }),
  senha: z.string().min(6, { message: 'A senha deve ter pelo menos 6 caracteres.' })
})

type FormData = z.infer<typeof empresaSchema>

export default function FormEmpresaParceira() {
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
    console.log(values)
    try {
      const response = await api.post('http://localhost:8080/api/empresas-parceiras', values)

      if (response.status === 201 || response.status === 200) {
        queryClient.invalidateQueries({ queryKey: ['empresas'] })
        alert('Empresa cadastrada com sucesso!')
        form.reset()
      }
    } catch (error) {
      console.error(error)
      alert('Erro ao cadastrar empresa.')
    }
  }

  return (
    <div className='w-full flex items-center justify-center'>
    <Form {...form}>
      <form
        onSubmit={form.handleSubmit(handleSubmit)}
        className="flex flex-col justify-center items-center-safe gap-y-4 w-md"
      >
        <h1 className="font-bold text-2xl">Cadastro de Empresa Parceira</h1>

        {/* CNPJ */}
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

        {/* Razão Social */}
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

        {/* Email */}
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

        {/* Senha */}
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

        <Button type="submit" className="mt-4">
          Cadastrar
        </Button>
      </form>
    </Form>
    </div>
  )
}
