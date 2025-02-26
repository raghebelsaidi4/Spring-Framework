## Deploying REST API to Cloud Platforms
### Why Local APIs Are Not Publicly Accessible?
When we run our REST API locally, it is only accessible within our machine. To make it publicly accessible, we need to deploy it to a cloud platform.

### Popular Cloud Platforms:
1. **AWS (Amazon Web Services)**
2. **Azure**
3. **Google Cloud**
4. **Oracle Cloud**
5. **Heroku**

### Deploying to Heroku
Heroku is a PaaS (Platform as a Service) provided by Salesforce. It allows us to deploy up to 5 applications for free.

**Steps to Deploy to Heroku:**
1. Install Heroku CLI.
2. Login to Heroku: `heroku login`
3. Create a new Heroku app: `heroku create`
4. Push the project: `git push heroku main`
5. Access the deployed API via the Heroku-provided URL.

### AWS vs. Heroku
| Feature  | AWS  | Heroku  |
|----------|------|---------|
| Type | IaaS (Infrastructure as a Service) | PaaS (Platform as a Service) |
| Cost | Pay-as-you-go | Free for 5 apps |
| Flexibility | High (Customizable infrastructure) | Limited (Managed services) |

### PaaS vs. IaaS
- **PaaS** (Platform as a Service): Provides a managed platform for deploying applications (e.g., Heroku, Google App Engine).
- **IaaS** (Infrastructure as a Service): Provides virtual machines, storage, and networking capabilities (e.g., AWS EC2, Google Compute Engine, Microsoft Azure Virtual Machines). IaaS allows users to configure and manage their own operating systems, applications, and development environments while offering scalability, flexibility, and cost-efficiency compared to on-premises infrastructure.

