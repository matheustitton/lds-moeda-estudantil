'use client'

import { useUsuario } from '@/contexts/user.context'
import { Form, FormControl, FormField, FormItem } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card'
import { alunoSchema } from '@/app/login/_components/AlunoForm'
import { TipoUsuario } from '@/types/Usuario/enum'
import { zodResolver } from '@hookform/resolvers/zod'
import { useForm } from 'react-hook-form'
import { AlunoResponse, UsuarioResponse } from '@/types/Usuario/usuario.response'
import z from 'zod'
import { Button } from '@/components/ui/button'
import { Pencil, Trash } from 'lucide-react'
import { AlunoRequisicao, AlunoUpdateRequest } from '@/server/Aluno'
import { Utils } from '@/lib/utils/utils'
import { toast } from 'sonner'
import { useRouter } from 'next/navigation'
import { useState } from 'react'

export default function PerfilAluno() {
  const { usuario } = useUsuario()
  const router = useRouter();

  const [modoEdicao, setModoEdicao] = useState(false);
  const decode = Utils.Sessao.decodificarToken()

  const form = useForm<z.infer<typeof alunoSchema>>({
      mode: 'onTouched',
      resolver: zodResolver(alunoSchema),
      defaultValues: {
        nome: (usuario as AlunoResponse).nome,
        rg: (usuario as AlunoResponse).rg,
        cpf: (usuario as AlunoResponse).cpf,
        curso: (usuario as AlunoResponse).curso,
        email: (usuario as AlunoResponse).email,
        senha: '',
        tipo: TipoUsuario.ALUNO,
        instituicaoEnsino: undefined
      }
    })

  if (!usuario) {
    return (
      <div className="flex justify-center items-center h-full">
        <p>Aluno não encontrado ou usuário inválido.</p>
      </div>
    )
  }

  const handleEditar = () => {

    const data = form.getValues();

    const alunoUpdate: AlunoUpdateRequest = {
      nome: data.nome,
      curso: data.curso,
      email: data.email,
      instituicaoEnsino: (usuario as AlunoResponse).instituicao.id,
    }
    
    AlunoRequisicao.Editar(Number(decode?.sub), alunoUpdate).then((res) => {
      setModoEdicao(false)
      if(res.statusCode < 300){
        router.refresh()
      }
      else{
        form.reset()
        toast.error("Erro ao editar usuário");
      }
    })
  }

  return (
    <div className="flex justify-center py-10">
      <Card className="w-full max-w-2xl">
        <CardHeader>
          <CardTitle>Perfil do Aluno</CardTitle>
        </CardHeader>
        <CardContent className='flex flex-col gap-y-3'>
          <Form {...form}>
            <div className="grid gap-4">
              <FormField
                control={form.control}
                name="nome"
                render={({field}) => (
                  <FormItem>
                    <FormControl>
                      <Input
                        onChange={field.onChange}
                        value={field.value}
                        readOnly={!modoEdicao}
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
                        onChange={field.onChange}
                        value={field.value}
                        readOnly={!modoEdicao}
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
                        onChange={field.onChange}
                        value={field.value}
                        readOnly={!modoEdicao}
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
                        onChange={field.onChange}
                        value={field.value}
                        readOnly={!modoEdicao}
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
                        value={field.value}
                        readOnly={!modoEdicao}
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
                        onChange={field.onChange}
                        value={(usuario as AlunoResponse).instituicao.nome}
                        disabled
                        readOnly={!modoEdicao}
                        className="h-12 text-lg border-2 border-gray-200 rounded-xl bg-gray-100"
                      />
                    </FormControl>
                  </FormItem>
                )}
              />
            </div>
          </Form>
          <div className='flex justify-end gap-x-1'>
            <Button onClick={async () => {
              if(!modoEdicao){
                setModoEdicao(true)
              } else{
                handleEditar()
              }
            }}>
              <Pencil/> Editar
            </Button>
            <Button 
              onClick={ async () => {
                
                AlunoRequisicao.Excluir(Number(decode?.sub)).then((response) => {
                  if(response.statusCode == 204){
                    toast.success("Usuário excluído com sucesso.");
                    router.push("/login");
                  }
                  else{
                    toast.error("Erro ao excluir a conta.")
                  }

                })
              }}
              className='bg-secondary hover:bg-secondary'
            >
              <Trash/> Excluir conta
            </Button>
          </div>
        </CardContent>
      </Card>
    </div>
  )
}
