# BJJ Copilot Frontend

Angular-based frontend application for the BJJ Copilot system.

## Prerequisites

- Node.js 20+
- npm 10+

## Setup

```bash
# Install dependencies
npm install

# Start development server
npm start

# The app will be available at http://localhost:4200
```

## Available Scripts

- `npm start` - Start development server
- `npm run build` - Build for production
- `npm test` - Run unit tests
- `npm run test:watch` - Run tests in watch mode
- `npm run lint` - Run ESLint
- `npm run e2e` - Run end-to-end tests

## Architecture

This Angular application follows the architecture defined in the main [ARCHITECTURE.md](../ARCHITECTURE.md) document and includes:

- **TypeScript** for type-safe development
- **Angular Material** for UI components
- **RxJS** for reactive programming
- **Angular Router** for routing
- **SCSS** for styling

## Development

The application is configured with:
- Strict TypeScript configuration
- ESLint for code quality
- Karma + Jasmine for testing
- Angular CLI for build tooling

## Build

Production builds are optimized and include:
- Tree shaking
- Minification
- Bundle optimization
- Source maps (in development)

## Original Angular CLI Information

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 20.0.5.

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
