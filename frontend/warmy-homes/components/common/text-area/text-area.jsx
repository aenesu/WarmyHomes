import { adventPro } from '@/app/(public)/layout';
import styles from "./text-area.module.scss";

export default function TextArea({ label = 'text-input', height = '200px', width = '866px', resize= 'none'}) {
    return (
        <div className={`${adventPro.className} ${styles.inputField}`} style={{ width }}>
            <textarea
                id={label}
                name={label}
                placeholder=' '
                style={{ height, resize}}
                
                >
            </textarea>
            <label htmlFor={label} className={adventPro.className} >
                {label
                    .split('-')
                    .map(word => word.charAt(0).toUpperCase() + word.slice(1))
                    .join(' ')
                }
            </label>
        </div>
    );
}
