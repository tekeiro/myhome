import { GeneratorConfig } from 'ng-openapi';

const config: GeneratorConfig = {
  input: 'http://localhost:8080/v3/api-docs',
  output: './src/client',
  clientName: 'MyHomeApiClient',
  options: {
    dateType: 'Date',
    enumStyle: 'enum',
  },
  compilerOptions: {
    
  }
};
export default config;