import {unstable_noStore} from "next/cache";

export const fetchPhysician = async () => {
    unstable_noStore()
    const response = await fetch(`${process.env.NEXT_PUBLIC_api_layer_host}/api/physician`)
    const physicians = await response.json()
    return physicians;
}

export const fetchAppointment = async () => {
    unstable_noStore()
    const response = await fetch(`${process.env.NEXT_PUBLIC_api_layer_host}/api/appointment`);
    const appointments = await response.json();
    return appointments;
}
