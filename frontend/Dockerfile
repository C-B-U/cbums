FROM node:14-alpine AS builder

# Create node_modules directory
WORKDIR /app

# Install app dependecies
# This will only run when the package.json is changed
COPY package.json .
RUN npm install

FROM node:14-alpine

# Copy node_modules from previous stage image
RUN mkdir -p /node_modules
COPY --from=builder /app/node_modules /app/node_modules

# Create app diretory
WORKDIR /app/frontend

# Bundle app source

COPY . .

# Set enviroment variable for React app URL

ARG REACT_APP_API_BASE_URL
ENV REACT_APP_API_BASE_URL=&API_BASE_URL

#Create symlink to /backend --





ENV PATH /app/node_modules/.bin:$PATH

CMD ["npm", "start"]