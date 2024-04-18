'use client'

import {useMessage} from "@/app/ui/dashboard/chat/chat-context";
import Image from "next/image";

export default function MessageList() {
    const { messages, isStreaming } = useMessage();

    return (
        <div className="mx-auto max-w-3xl pt-8">
            {messages?.map((message, i) => {
                const isUser = message.role === 'user';
                if (message.role === 'system') return null;
                return (
                    <div
                        id={`message-${i}`}
                        className={`fade-up mb-4 flex ${isUser ? 'justify-end' : 'justify-start'}`}
                        key={message.content}
                    >
                        {!isUser && (
                            <Image
                                src="/favicon.png"
                                height={36}
                                width={36}
                            />
                        )}
                        <div
                            className={`group relative ml-2 mr-2 rounded-lg px-3 py-2 ${
                                isUser
                                    ? 'bg-blue-500 text-white'
                                    : 'bg-gray-100 text-gray-800'
                            }`}
                        >
                            {message.content.trim()}
                        </div>
                        {isUser && (
                            <img
                                src="/profile-image.png"
                                className="h-9 w-9 cursor-pointer rounded-full"
                                alt="avatar"
                            />
                        )}
                    </div>
                )
            })}
            {isStreaming && (
                <div className="mb-4 flex justify-start">
                    <img
                        src="https://www.teamsmart.ai/next-assets/team/ai.jpg"
                        className="h-9 w-9 rounded-full"
                        alt="avatar"
                    />
                    <div className="loader relative ml-2 flex items-center justify-between space-x-1.5 rounded-full bg-gray-100 p-2.5">
                        <span className="block h-3 w-3 rounded-full bg-blue-500"></span>
                        <span className="block h-3 w-3 rounded-full bg-blue-500"></span>
                        <span className="block h-3 w-3 rounded-full bg-blue-500"></span>
                    </div>
                </div>
            )}
        </div>
    )
}
