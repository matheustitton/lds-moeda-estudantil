'use client'

import { useUsuario } from '@/contexts/user.context'
import { Form, FormControl, FormField, FormItem } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card'
import { alunoSchema } from '@/app/login/_components/AlunoForm'
import { TipoUsuario } from '@/types/Usuario/enum'
import { zodResolver } from '@hookform/resolvers/zod'
import { useForm } from 'react-hook-form'
import { AlunoResponse } from '@/types/Usuario/usuario.response'
import z from 'zod'

export default function PerfilAluno() {
  const { usuario } = useUsuario()

  const form = useForm<z.infer<typeof alunoSchema>>({
      mode: 'onTouched',
      resolver: zodResolver(alunoSchema),
      defaultValues: {
        nome: '',
        rg: '',
        cpf: '',
        curso: '',
        email: '',
        senha: '',
        tipo: TipoUsuario.ALUNO,
        instituicaoEnsino: undefined
      }
    })

    console.log(usuario)

  if (!usuario) {
    return (
      <div className="flex justify-center items-center h-full">
        <p>Aluno não encontrado ou usuário inválido.</p>
      </div>
    )
  }

  return (
    <div className="flex justify-center py-10">
      <Card className="w-full max-w-2xl">
        <CardHeader>
          <CardTitle>Perfil do Aluno</CardTitle>
        </CardHeader>
        <CardContent>
          <Form {...form}>
            <div className="grid gap-4">
              <FormField
                control={form.control}
                name="nome"
                render={({field}) => (
                  <FormItem>
                    <FormControl>
                      <Input
                        {...field}
                        value={(usuario as AlunoResponse).nome}
                        readOnly
                        className="h-12 text-lg border-2 border-gray-200 rounded-xl bg-gray-100"
                      />
                    </FormControl>
                  </FormItem>
                )}
              />

              <FormField
                control={form.control}
                name="email"
                render={({field}) => (
                  <FormItem>
                    <FormControl>
                      <Input
                        {...field}
                        value={(usuario as AlunoResponse).email}
                        readOnly
                        className="h-12 text-lg border-2 border-gray-200 rounded-xl bg-gray-100"
                      />
                    </FormControl>
                  </FormItem>
                )}
              />

              <FormField
                control={form.control}
                name="cpf"
                render={({field}) => (
                  <FormItem>
                    <FormControl>
                      <Input
                        {...field}
                        value={(usuario as AlunoResponse).cpf}
                        readOnly
                        className="h-12 text-lg border-2 border-gray-200 rounded-xl bg-gray-100"
                      />
                    </FormControl>
                  </FormItem>
                )}
              />

              <FormField
                control={form.control}
                name="rg"
                render={({field}) => (
                  <FormItem>
                    <FormControl>
                      <Input
                        {...field}
                        value={(usuario as AlunoResponse).rg}
                        readOnly
                        className="h-12 text-lg border-2 border-gray-200 rounded-xl bg-gray-100"
                      />
                    </FormControl>
                  </FormItem>
                )}
              />

              <FormField
                control={form.control}
                name="curso"
                render={({field}) => (
                  <FormItem>
                    <FormControl>
                      <Input
                        {...field}
                        value={(usuario as AlunoResponse).curso}
                        readOnly
                        className="h-12 text-lg border-2 border-gray-200 rounded-xl bg-gray-100"
                      />
                    </FormControl>
                  </FormItem>
                )}
              />

              <FormField
                control={form.control}
                name="instituicaoEnsino"
                render={({field}) => (
                  <FormItem>
                    <FormControl>
                      <Input
                        {...field}
                        value={(usuario as AlunoResponse).instituicao?.nome || ''}
                        readOnly
                        className="h-12 text-lg border-2 border-gray-200 rounded-xl bg-gray-100"
                      />
                    </FormControl>
                  </FormItem>
                )}
              />
            </div>
          </Form>
        </CardContent>
      </Card>
    </div>
  )
}
