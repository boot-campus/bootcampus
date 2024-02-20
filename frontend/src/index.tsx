import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import GlobalStyle from '~styles/GlobalStyle';
import { theme } from '~styles/theme';
import App from './App';
import { ThemeProvider } from 'styled-components';

const root = createRoot(document.getElementById('root') as HTMLElement);

root.render(
  <StrictMode>
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <App />
    </ThemeProvider>
  </StrictMode>,
);
