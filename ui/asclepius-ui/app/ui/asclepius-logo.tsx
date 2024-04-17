import {lusitana} from "@/app/lib/font";
import Image from "next/image";

export default function AsclepiusLogo() {
    return (
        <div className={`${lusitana.className} flex flex-row items-center leading-none text-white`}>
            <Image
                src="/favicon-outline.png"
                height={64}
                width={64}
                className="h-12 w-12"
            />
            <p className="text-[35px]">Asclepius</p>
        </div>
    )
}