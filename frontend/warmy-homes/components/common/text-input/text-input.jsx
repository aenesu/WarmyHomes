import { adventPro } from "@/app/layout";
import styles from "./text-input.module.scss";

export default function TextInput({ label = 'text-input', type = 'text', placeholder, value, height = '55px', width = '866px' }) {
    return (
        <div className={`${adventPro.className} ${styles.inputField}`} style={{ width }}>
            <input
                type={type}
                id={label}
                name={label}
                placeholder={placeholder || " "}
                style={{ height }}
                defaultValue={value}
            />
            <label htmlFor={label} className={adventPro.className}>
                {label
                    .split('-')
                    .map(word => word.charAt(0).toUpperCase() + word.slice(1))
                    .join(' ')
                }
            </label>
        </div>
    );
}
