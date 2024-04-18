'use client'

import {
    CalendarDaysIcon,
    ChatBubbleLeftRightIcon,
    ClipboardDocumentCheckIcon,
    InboxStackIcon
} from "@heroicons/react/24/outline";
import {usePathname} from "next/navigation";
import Link from "next/link";
import clsx from "clsx";

const links= [
    {name: 'Chat', href: '/dashboard/chat', icon: ChatBubbleLeftRightIcon},
    {name: 'Appointment', href: 'appointment', icon: CalendarDaysIcon},
    // {name: 'Messages', href: '/messages', icon: InboxStackIcon},
    {name: 'Note & Summary', href: 'note', icon: ClipboardDocumentCheckIcon}
]

export default function NavLinks() {
    const pathname = usePathname();
    return(
        <>
            {
                links.map(link => {
                    const Icon = link.icon;
                    return(
                        <Link
                            key = {link.name}
                            href = {link.href}
                            className={clsx("flex h-[48px] grow items-center justify-center gap-2 rounded-md bg-gray-50 p-3 text-sm font-medium hover:bg-sky-100 hover:text-blue-600 md:flex-none md:justify-start md:p-2 md:px-3",
                                {'bg-sky-100 text-blue-600': pathname === link.href,}
                            )}
                        >
                            <Icon className="w-6"/>
                            <p className="hidden md:block">{link.name}</p>
                        </Link>
                    )
                })
            }
        </>
    )
}