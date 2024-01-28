declare module '*.png';

declare module '*.webp';

declare module '*.jpg';

declare module '*.gif';

declare module '*.svg' {
  export const ReactComponent: React.FunctionComponent<
    React.SVGProps<SVGSVGElement> & { title?: string }
  >;

  const src: string;
  export default src;
}
