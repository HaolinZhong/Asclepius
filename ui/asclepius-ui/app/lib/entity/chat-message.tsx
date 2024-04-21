export enum ChatMessageRoleEnum {
    User = 'user',
    Assistant = 'assistant'
}

export interface ChatMessage {
    'type': string
    'role': ChatMessageRoleEnum
    'author'?: String
    'content': String
    'data'?
}